import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Animal } from './shelter';
import { ShelterService } from './shelter.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  // title = 'shelterManagerApp';
  public animals: Animal[] = [];

  constructor(private animalService: ShelterService) {}

  ngOnInit() {
    this.getAnimals();
  }


  public getAnimals(): void {
    this.animalService.getAnimals().subscribe(
      (response: Animal[]) => {
        this.animals = response;
      },
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

}
