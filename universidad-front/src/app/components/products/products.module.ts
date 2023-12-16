import { NgModule } from '@angular/core';
import { ProductsComponent } from './products.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [],
  imports: [RouterModule.forChild([
    { path: '', component: ProductsComponent }
])
  ]
})
export class ProductsModule { }
