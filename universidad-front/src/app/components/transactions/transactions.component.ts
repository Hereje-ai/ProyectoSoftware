import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Subscription } from 'rxjs';
import { Product } from 'src/app/domain/product';
import { Suplier } from 'src/app/domain/suplier';
import { Transaction } from 'src/app/domain/transaction';
import { TypeTransaction } from 'src/app/domain/typeTransaction';
import { Usuarios } from 'src/app/domain/usuarios';
import { ProductsService } from 'src/app/service/products.service';
import { TransactionsService } from 'src/app/service/transactions.service';
import { UsuariosService } from 'src/app/service/usuarios.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.scss']
})
export class TransactionsComponent implements OnDestroy, OnInit {
  idProduct: string = ""

  subProduct: Subscription = new Subscription
  subSuplier: Subscription = new Subscription
  subCategory: Subscription = new Subscription
  subUsers: Subscription = new Subscription
  subTransaction: Subscription = new Subscription

  transactionSelect: Transaction = new Transaction

  transaction: Transaction = new Transaction
  transactionForm: FormGroup 

  lstTransaction: Transaction[] = new Array

  displayCreate: boolean = false
  edit: boolean = false
  transactionTypeId: number = 0
  productId: number = 0
  documentNumber: String = "";
  typeUser: number = 0

  idCategory: Number = 0
  idSuplier: Number = 0

  lstSuplier: Suplier[] = new Array
  lstProduct: Product[] = new Array
  lstTypeTransaction: any[] = new Array
  lstUser : Usuarios[] = new Array

  constructor(private service: TransactionsService, private messageService: MessageService,
    private confirmationService: ConfirmationService, private productService: ProductsService, private userService: UsuariosService) {

      this.lstTypeTransaction = [{ value: 1, name: "Entrada" }, { value: 2, name: "Salida" }]


    this.transactionForm = new FormGroup({
      id: new FormControl(''),
      amount: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
    })
  }

  /**
   * componente encargado de destruir las subscripciones
   */
  ngOnDestroy(): void {

  }
  /**
 * se encarga de correr una sola vez al carga la pagina
 */
  ngOnInit(): void {
    this.getProduct()
    this.getTransaction()
    this.getUsers()
  }

  getSuplierForm(id : Number): string | undefined{
    let name :string | undefined
    for( let suplier of this.lstSuplier){

      if(suplier.id == id){
        name = suplier.name
      }
    }
    return name;
  }

  getTypeTransaction(id: Number){
    let name :string | undefined
    for( let type of this.lstTypeTransaction){
      if(type.value == id){
        name = type.name
      }
    }
    return name;
  }

  /**
   * metodo que se encarga de tomar el documento y cargarlo para ser enviado al service
   */
  getTransaction(): void {
    this.subTransaction = this.service.getTransactionAll().subscribe({
      next: data => {
        this.lstTransaction = data
      }, error: error => {

      }, complete: () => {

      }
    })
  }

  /**
   * metodo que se encarga de tomar el documento y cargarlo para ser enviado al service
   */
  getProduct(): void {
    this.subProduct = this.productService.getProductAll().subscribe({
      next: data => {
        this.lstProduct = data
      }, error: error => {

      }, complete: () => {

      }
    })
  }

  /**
   * metodo que se encarga de tomar el documento y cargarlo para ser enviado al service
   */
  getUsers(): void {
    this.subUsers = this.userService.getUser().subscribe({
      next: data => {
        this.lstUser = data
      }, error: error => {

      }, complete: () => {

      }
    })
  }

  getproductName(productId : number){
    console.log("aquisebas")
    let name :string | undefined
    for( let product of this.lstProduct){
      console.log("aquisebas : " + productId + "   " + product.id) 
      if(product.id == productId){
        name = product.name
      }
    }
    return name;
  }

  showDisplayCreate(): void {
    this.transactionSelect = new Transaction
    this.limpiarForm()
    this.edit = false
    this.displayCreate = true
  }

  showDisplayEdit(transaction: Transaction): void {
    this.transaction = transaction
    this.cargaForm()
    this.edit = true
    this.displayCreate = true
  }

  confirmarCreateTransaction(): void {
    this.confirmationService.confirm({
      message: '¿Estas seguro que deseas realizar esta transaccion?',
      header: 'Create Confirmation',
      icon: 'pi pi-info-circle',
      accept: () => {
        this. createTransaction()
        this.getTransaction()

      }, key: 'createTrasaction'
    })
  }

  createTransaction(): void {
    if(this.transactionForm.valid){
      this.transactionSelect.productId = this.productId
      this.transactionSelect.amount = Number(this.transactionForm.controls['amount'].value)
      this.transactionSelect.description = String(this.transactionForm.controls['description'].value)
      this.transactionSelect.documentNumber = this.documentNumber
      this.transactionSelect.transactionTypeId = this.transactionTypeId

      this.service.createTransaction(this.transactionSelect).subscribe({
        next: data => {
          
          if (data != null) {
            this.showSuccessViaToast("Transaccion realizada correctamente")
            this.displayCreate = false
            this.getTransaction
          } else {
          
            this.showErrorViaToast("error creando productos")
          }
        }, error: error => {

        }, complete: () => {

        },
      })

    } else {
      this.validaCampos()
    }

  }

  confirmarEditTransaction(): void {
    this.confirmationService.confirm({
      message: '¿Estas seguro que deseas editar esta transacción?',
      header: 'Editar Confirmation',
      icon: 'pi pi-info-circle',
      accept: () => {
        this.editarTransaction()
        this.getTransaction()

      }, key: 'editarTransaction'
    })
  }

  editarTransaction(): void {
    if(this.transactionForm.valid){
      this.transactionSelect.id = Number(this.transactionForm.controls['id'].value)
      this.transactionSelect.productId = this.productId
      this.transactionSelect.amount = Number(this.transactionForm.controls['amount'].value)
      this.transactionSelect.description = String(this.transactionForm.controls['description'].value)
      this.transactionSelect.documentNumber = this.documentNumber
      this.transactionSelect.transactionTypeId = this.transactionTypeId

      this.service.editTransaction(this.transactionSelect).subscribe({
        next: data => {
          if (data != null) {
            this.showSuccessViaToast("Transaccion editada correctamente")
            this.displayCreate = false
            this.getTransaction()
          } else {
            this.showErrorViaToast("error editando transacción")
          }
        }, error: error => {

        }, complete: () => {

        },
      });
    }else{
      this.validaCampos();
    }
  }

  //------------------------------------------------------------------------------

  cargaForm(): void {
    this.transactionForm = new FormGroup({
      id: new FormControl(String(this.transactionSelect.id)),
      amount: new FormControl(Number(this.transactionSelect.amount)),
      description: new FormControl(String(this.transactionSelect.description))
    })
  }

  limpiarForm(): void {
    this.transactionForm = new FormGroup({
      id: new FormControl(''),
      amount: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
    })
  }

  validaCampos(): void {

    if (!this.transactionForm.controls['amount'].valid) {
      this.showErrorViaToast("Escribe la cantidad a asignar")
    }
    else if (!this.transactionForm.controls['description'].valid) {
      this.showErrorViaToast("Escribir la cantidad del producto")
    }
  }

  showSuccess(message: string) {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: message,life:2000 })
  }

  showError(message: string) {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: message, life:2000 })
  }

  showErrorViaToast(message: string) {
    this.messageService.add({ key: 'tst', severity: 'error', summary: 'Error Message', detail: message,life: 3000 });
}

showSuccessViaToast(message: string) {
  this.messageService.add({ key: 'tst', severity: 'success', summary: 'Success Message', detail: message });
}

}
