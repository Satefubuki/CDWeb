import { Component, OnInit } from '@angular/core';
import { Paging } from './../../models/paging';
import { LogService } from './../../services/log.service';
import { Log } from './../../models/log';

@Component({
  selector: 'app-log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.scss']
})
export class LogComponent implements OnInit {
  paging = { page: 0, pageLimit: 5, totalItems: 3 } as Paging;
  logs: Log[] = [];
  constructor(private logService: LogService) { }

  ngOnInit() {
    this.loadLog(null);
  }

  loadLog(page) {
    if (page != null) {
      this.paging.page = page.offset;
    }
    this.logService.getAllInPage(this.paging.page, this.paging.pageLimit).subscribe(res => {
      this.logs = res.content;
      console.log(this.logs);
      this.paging.page = res.number;
      this.paging.pageLimit = res.size;
      this.paging.totalItems = res.totalElements;
      this.paging.totalPages = res.totalPages;
    });
  }

}
