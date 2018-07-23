import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  searchCond: string;
  constructor() { }

  setSearchCond(str: string) {
    this.searchCond = str;
  }

  getSearchCond(): string {
    return this.searchCond;
  }
}
