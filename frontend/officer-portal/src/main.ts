import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
 selector:'app-root',
 standalone:true,
 imports:[FormsModule],
 template: `
 <main class="shell">
  <header><div><span class="eyebrow">CORE BANKING • OFFICER WORKBENCH</span><h1>Semantic Schema Mapping Agent</h1><p>Translate operational requests into governed legacy-schema actions.</p></div><span class="status">● DEMO ENVIRONMENT</span></header>
  <section class="grid">
   <div class="card">
    <h2>Officer Request</h2>
    <label>Officer ID</label><input [(ngModel)]="officerId" placeholder="OFFICER-1001">
    <label>Natural-language request</label><textarea [(ngModel)]="request" rows="6" placeholder="Map a loan repayment request for the customer account..."></textarea>
    <button (click)="map()" [disabled]="loading">{{loading?'Analyzing...':'Analyze & Map'}}</button>
   </div>
   <div class="card result" *ngIf="result">
    <h2>Agent Decision</h2>
    <div class="metric"><span>Intent</span><strong>{{result.intent}}</strong></div>
    <div class="metric"><span>Legacy Code</span><strong>{{result.legacyCode || '—'}}</strong></div>
    <div class="metric"><span>Business Term</span><strong>{{result.businessTerm || '—'}}</strong></div>
    <div class="metric"><span>Confidence</span><strong>{{result.confidence | number:'1.0-2'}}%</strong></div>
    <div class="decision">{{result.status}}</div>
    <p>{{result.message}}</p>
    <button class="approve" *ngIf="result.status==='PENDING_APPROVAL'" (click)="approve(true)">Approve Mapping</button>
   </div>
  </section>
 </main>`,
 styles: []
})
export class AppComponent {
 officerId='OFFICER-1001'; request=''; loading=false; result:any=null;
 constructor(private http:HttpClient){}
 map(){this.loading=true;this.http.post<any>('/api/v1/semantic/map',{transactionId:'',officerId:this.officerId,request:this.request}).subscribe({next:r=>{this.result=r;this.loading=false},error:e=>{this.result={intent:'ERROR',status:'ERROR',message:e.message,confidence:0};this.loading=false}})}
 approve(approved:boolean){this.http.post<any>('/api/v1/semantic/approve',{transactionId:this.result.transactionId,officerId:this.officerId,approved}).subscribe(r=>this.result=r)}
}
bootstrapApplication(AppComponent,{providers:[provideHttpClient()]}).catch(console.error);
