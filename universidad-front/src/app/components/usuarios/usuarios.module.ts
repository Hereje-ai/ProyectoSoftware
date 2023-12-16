import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { UsuariosComponent } from './usuarios.component';




@NgModule({
  declarations: [],
  imports: [RouterModule.forChild([
    { path: '', component: UsuariosComponent }
])
  ]
})
export class UsuariosModule { }
