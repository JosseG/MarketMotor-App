import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit{


  @Input() currentPage: number = 0;
  @Input() totalPages: number = 0;

  constructor() {}

  ngOnInit(): void {}

  onPageChange(page: number): void {
    console.log(`Page changed to ${page}`);
  }

}
