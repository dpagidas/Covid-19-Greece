// import {Pageable} from './pageable.interface';
// import {Sort} from './sort.interface';


export interface Page<T> {
  data: T[];
  totalPages: number;
  totalElements: number;

}
