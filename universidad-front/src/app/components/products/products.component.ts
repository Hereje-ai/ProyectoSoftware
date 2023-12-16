import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { Usuarios } from '../../domain/usuarios';
import { UsuariosService } from '../../service/usuarios.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Product } from 'src/app/domain/product';
import { ProductsService } from 'src/app/service/products.service';
import { Suplier } from 'src/app/domain/suplier';
import { Category } from 'src/app/domain/category';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnDestroy, OnInit {
  idProduct: string = ""

  subProduct: Subscription = new Subscription
  subSuplier: Subscription = new Subscription
  subCategory: Subscription = new Subscription

  productSelect: Product = new Product

  products: Product = new Product
  productForm: FormGroup 

  lstProducts: Product[] = new Array

  displayCreate: boolean = false
  edit: boolean = false
  campa: number = 0
  typeUser: number = 0

  idCategory: Number = 0
  idSuplier: Number = 0

  lstSuplier: Suplier[] = new Array
  lstCategory: Category[] = new Array

  constructor(private service: ProductsService, private messageService: MessageService,
    private confirmationService: ConfirmationService) {


    this.productForm = new FormGroup({
      id: new FormControl(''),
      name: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
      quantityAvailable: new FormControl('', Validators.required),
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
    this.getCategory()
    this.getSuplier()
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

  getCategoryForm(id : Number): string | undefined{
    let name :string | undefined
    for( let category of this.lstCategory){

      if(category.id == id){
        name = category.name
      }
    }
    return name;
  }

  /**
   * metodo que se encarga de tomar el documento y cargarlo para ser enviado al service
   */
  getSuplier(): void {
    this.subSuplier = this.service.getSuplierAll().subscribe({
      next: data => {
        this.lstSuplier = data
      }, error: error => {

      }, complete: () => {

      }
    })
  }

  /**
   * metodo que se encarga de tomar el documento y cargarlo para ser enviado al service
   */
  getCategory(): void {
    this.subCategory = this.service.getCategoryAll().subscribe({
      next: data => {
        this.lstCategory = data
      }, error: error => {

      }, complete: () => {

      }
    })
  }

  /**
   * metodo que se encarga de tomar el documento y cargarlo para ser enviado al service
   */
  getProduct(): void {
    this.subProduct = this.service.getProductAll().subscribe({
      next: data => {
        this.lstProducts = data
      }, error: error => {

      }, complete: () => {

      }
    })
  }

  showDisplayCreate(): void {
    this.productSelect = new Product
    this.limpiarForm()
    this.edit = false
    this.displayCreate = true
  }

  showDisplayEdit(user: Product): void {
    this.productSelect = user
    this.cargaForm()
    this.edit = true
    this.displayCreate = true
  }


  typeUserSelect(type: number): string {

    let result: string = ""
    if (type == 1) {
      result = "Admin"
    }
    else if (type == 2) {
      result = "User"
    }
    return result;
  }


  confirmarCreateProduct(): void {
    this.confirmationService.confirm({
      message: '¿Estas seguro que deseas guardar este producto?',
      header: 'Create Confirmation',
      icon: 'pi pi-info-circle',
      accept: () => {
        console.log("aquisebas2")
        this.createProduct()

      }, key: 'createProduct'
    })
  }

  createProduct(): void {
    console.log("aquisebas3")
    if(this.productForm.valid){
      console.log("aquisebas4")
      this.productSelect.name = String(this.productForm.controls['name'].value)
      this.productSelect.description = String(this.productForm.controls['description'].value)
      this.productSelect.price = Number(this.productForm.controls['price'].value)
      this.productSelect.quantityAvailable = Number(this.productForm.controls['quantityAvailable'].value)
      this.productSelect.categoryId = Number(this.idCategory)
      this.productSelect.suplierId = Number(this.idSuplier)

      this.service.createProduct(this.productSelect).subscribe({
        next: data => {
          console.log("aquisebas2")
          if (data != null) {
            console.log("aquisebas")
            this.showSuccessViaToast("Producto creado")
            this.displayCreate = false
            this.getProduct()
          } else {
            console.log("aquisebas2")
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

  confirmarEditProduct(): void {
    this.confirmationService.confirm({
      message: '¿Estas seguro que deseas editar este producto?',
      header: 'Editar Confirmation',
      icon: 'pi pi-info-circle',
      accept: () => {
        this.editarProduct()

      }, key: 'editarProduct'
    })
  }

  editarProduct(): void {
    if(this.productForm.valid ){
      this.productSelect.id = Number(this.productForm.controls['id'].value)
      this.productSelect.name = String(this.productForm.controls['name'].value)
      this.productSelect.description = String(this.productForm.controls['description'].value)
      this.productSelect.price = Number(this.productForm.controls['price'].value)
      this.productSelect.quantityAvailable = Number(this.productForm.controls['quantityAvailable'].value)
      this.productSelect.categoryId = Number(this.idCategory)
      this.productSelect.suplierId = Number(this.idSuplier)

      this.service.editProduct(this.productSelect).subscribe({
        next: data => {
          if (data != null) {
            this.showSuccessViaToast("Producto editado")
            this.displayCreate = false
            this.getProduct()
          } else {
            this.showErrorViaToast("error editando producto")
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
    this.productForm = new FormGroup({
      id: new FormControl(String(this.productSelect.id)),
      name: new FormControl(String(this.productSelect.name)),
      description: new FormControl(String(this.productSelect.description)),
      price: new FormControl(Number(this.productSelect.price)),
      quantityAvailable: new FormControl(String(this.productSelect.quantityAvailable)),

    })
  }

  limpiarForm(): void {
    this.productForm = new FormGroup({
      id: new FormControl(''),
      name: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
      quantityAvailable: new FormControl('', Validators.required),
    })
  }

  validaCampos(): void {
    if (!this.productForm.controls['name'].valid) {
      this.showErrorViaToast("Escribir el nombre del producto")
    }
    else if (!this.productForm.controls['description'].valid) {
      this.showErrorViaToast("Escribir la descripción del producto")
    }
    else if (!this.productForm.controls['price'].valid) {
      this.showErrorViaToast("Escribir el precio del producto")
    }
    else if (!this.productForm.controls['quantityAvailable'].valid) {
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
