import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Animal } from './shelter';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ShelterService{
    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) {}

    public getAnimals(): Observable<Animal[]> {
        return this.http.get<Animal[]>(`${this.apiServerUrl}/animals`);
    }

    public addAnimal(animal: Animal): Observable<Animal> {
        return this.http.post<Animal>(`${this.apiServerUrl}/animals`, animal);
    }

    public updateAnimal(animal: Animal): Observable<Animal> {
        return this.http.post<Animal>(`${this.apiServerUrl}/animals/${animal.id}`, animal);
    }

    public deleteAnimal(animalId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/animals/${animalId}`);
    }

}
