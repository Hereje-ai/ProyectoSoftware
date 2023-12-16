import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { TransactionsComponent } from './transactions.component';



@NgModule({
  declarations: [],
  imports: [RouterModule.forChild([
    { path: '', component: TransactionsComponent }
])
  ]
})
export class TransactionsModule { }
