import { Component, OnInit, ViewChild } from '@angular/core';
import { IgxNavigationDrawerComponent } from "igniteui-angular";


@Component({
  selector: 'app-admin-action',
  templateUrl: './admin-action.component.html',
  styleUrls: ['./admin-action.component.css']
})
export class AdminActionComponent implements OnInit {
  public navItems = [
    { name: "account_circle", text: "Avatar", link: "" },
    { name: "error", text: "Badge", link: "user" },
    { name: "group_work", text: "Button Group", link: "order" }
];

public selected = "Avatar";

public navigate(item) {
    this.selected = item.text;
}
  constructor() { }

  ngOnInit() {
  }
  

}
