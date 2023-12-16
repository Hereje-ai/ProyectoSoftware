import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Subscription } from 'rxjs';
import { Suplier } from 'src/app/domain/suplier';
import { SuplierService } from 'src/app/service/suplier.service';

@Component({
  selector: 'app-suplier',
  templateUrl: './suplier.component.html',
  styleUrls: ['./suplier.component.scss']
})
export class SuplierComponent implements OnDestroy, OnInit {
  idSuplier: string = ""

  subSuplier: Subscription = new Subscription

  SuplierSelect: Suplier = new Suplier

  Supliers: Suplier = new Suplier
  suplierForm: FormGroup 

  lstSupliers: Suplier[] = new Array

  displayCreate: boolean = false
  edit: boolean = false
  campa: number = 0
  typeUser: number = 0

  lstSuplier: Suplier[] = new Array

  constructor(private service: SuplierService, private messageService: MessageService,
    private confirmationService: ConfirmationService) {


    this.suplierForm = new FormGroup({
      id: new FormControl(''),
      name: new FormControl('', Validators.required),
      address: new FormControl('', Validators.required),
      telephone: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      nit: new FormControl('', Validators.required),
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
    this.getSuplier()
  }


  /**
   * metodo que se encarga de tomar el documento y cargarlo para ser enviado al service
   */
  getSuplier(): void {
    console.log("aquisebas")
    this.subSuplier = this.service.getSuplierAll().subscribe({
      next: data => {
        this.lstSuplier = data
      }, error: error => {

      }, complete: () => {

      }
    })
  }

  showDisplayCreate(): void {
    this.SuplierSelect = new Suplier
    this.limpiarForm()
    this.edit = false
    this.displayCreate = true
  }

  showDisplayEdit(user: Suplier): void {
    this.SuplierSelect = user
    this.cargaForm()
    this.edit = true
    this.displayCreate = true
  }

  confirmarCreateSuplier(): void {
    this.confirmationService.confirm({
      message: '¿Estas seguro que deseas guardar este proveedor?',
      header: 'Create Confirmation',
      icon: 'pi pi-info-circle',
      accept: () => {
        this.createSuplier()

      }, key: 'createSuplier'
    })
  }

  createSuplier(): void {
    if(this.suplierForm.valid){
      this.SuplierSelect.name = String(this.suplierForm.controls['name'].value)
      this.SuplierSelect.address = String(this.suplierForm.controls['address'].value)
      this.SuplierSelect.telephone = String(this.suplierForm.controls['telephone'].value)
      this.SuplierSelect.email = String(this.suplierForm.controls['email'].value)
      this.SuplierSelect.nit = Number(this.suplierForm.controls['nit'].value)

      this.service.createSuplier(this.SuplierSelect).subscribe({
        next: data => {
          if (data != null) {
            this.showSuccessViaToast("Proveedor creado")
            this.displayCreate = false
            this.getSuplier()
          } else {
            this.showErrorViaToast("error creando proveedor")
          }
        }, error: error => {

        }, complete: () => {

        },
      })

    } else {
      this.validaCampos()
    }

  }

  confirmarEditSuplier(): void {
    this.confirmationService.confirm({
      message: '¿Estas seguro que deseas editar este proveedor?',
      header: 'Editar Confirmation',
      icon: 'pi pi-info-circle',
      accept: () => {
        this.editarSuplier()

      }, key: 'editarSuplier'
    })
  }

  editarSuplier(): void {
    if(this.suplierForm.valid ){
      this.SuplierSelect.id = Number(this.suplierForm.controls['id'].value)
      this.SuplierSelect.name = String(this.suplierForm.controls['name'].value)
      this.SuplierSelect.address = String(this.suplierForm.controls['address'].value)
      this.SuplierSelect.telephone = String(this.suplierForm.controls['telephone'].value)
      this.SuplierSelect.email = String(this.suplierForm.controls['email'].value)
      this.SuplierSelect.nit = Number(this.suplierForm.controls['nit'].value)

      this.service.editSuplier(this.SuplierSelect).subscribe({
        next: data => {
          if (data != null) {
            this.showSuccessViaToast("Proveedor editado")
            this.displayCreate = false
            this.getSuplier()
          } else {
            this.showErrorViaToast("error editando proveedor")
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
    this.suplierForm = new FormGroup({
      id: new FormControl(String(this.SuplierSelect.id)),
      name: new FormControl(String(this.SuplierSelect.name)),
      address: new FormControl(String(this.SuplierSelect.address)),
      telephone: new FormControl(String(this.SuplierSelect.telephone)),
      email: new FormControl(String(this.SuplierSelect.email)),
      nit: new FormControl(Number(this.SuplierSelect.nit)),

    })
  }

  limpiarForm(): void {
    this.suplierForm = new FormGroup({
      id: new FormControl(''),
      name: new FormControl('', Validators.required),
      address: new FormControl('', Validators.required),
      telephone: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      nit: new FormControl('', Validators.required),
    })
  }

  validaCampos(): void {
    if (!this.suplierForm.controls['name'].valid) {
      this.showErrorViaToast("Escribir el nombre del proveedor")
    }
    else if (!this.suplierForm.controls['address'].valid) {
      this.showErrorViaToast("Escribir la dirección del proveedor")
    }
    else if (!this.suplierForm.controls['telephone'].valid) {
      this.showErrorViaToast("Escribir el contacto del proveedor")
    }
    else if (!this.suplierForm.controls['email'].valid) {
      this.showErrorViaToast("Escribir el correo del proveedor")
    }
    else if (!this.suplierForm.controls['nit'].valid) {
      this.showErrorViaToast("Escribir el nit del proveedor")
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
