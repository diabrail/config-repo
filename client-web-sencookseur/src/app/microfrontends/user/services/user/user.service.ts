import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {UserDto} from "../../models/user.dto";
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://your-backend-url'; // Remplacez par l'URL de votre backend

  constructor(private http: HttpClient) {}

  createUser(userDto: UserDto): Observable<any> {
    return this.http.post(`${this.baseUrl}/createUser`, userDto);
  }

  findAllUsers(page: number, size: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/findAllUsers?page=${page}&size=${size}`);
  }

  findUserById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/findUserById/${id}`);
  }


}
