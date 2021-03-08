import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
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
  public editAnimal!: Animal;
  public deleteAnimal!: Animal;

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

  public onOpenModal(animal: Animal, mode: string): void {
    const container = document.getElementById("main-container");
    const button = document.createElement('button');

    button.type = 'button'; // default is submit
    button.style.display = 'none'; // different button is already in UI
    button.setAttribute('data-toggle','modal')
    if (mode === 'add') {
      button.setAttribute('data-target','#addAnimalModal')
    } 
    else if (mode === 'edit') {
      this.editAnimal = animal;
      button.setAttribute('data-target','#updateAnimalModal')
    }      
    else if (mode === 'delete') {
      this.deleteAnimal = animal;
      button.setAttribute('data-target','#deleteAnimalModal')
    }  

    container?.appendChild(button);
    button.click();
  }

  public onAddAnimal(addForm: NgForm): void{
    document.getElementById('add-animal-form')?.click();
    this.animalService.addAnimal(addForm.value).subscribe(
      (response: Animal) => {
        console.log(response);
        this.getAnimals();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      } 
    );   

  }

  public onUpdateAnimal(animal: Animal): void {
    console.log(animal);
    this.animalService.updateAnimal(animal).subscribe(
      (response: Animal) => {
        console.log(response);
        this.getAnimals();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      } 
    );  
  }

  public onDeleteAnimal(animalId: number): void {
    this.animalService.deleteAnimal(animalId).subscribe(
      (response: void) => {
        console.log(response);
        this.getAnimals();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      } 
    );  
  }

  public searchAnimals(key: string): void {
    const results: Animal[] = [];
    for (const animal of this.animals) {
      if (   animal.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
          || animal.descr.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(animal);
      }
    }
    this.animals = results;
    if (results.length === 0 || !key) { //empty key
      this.getAnimals();
    }
  }

}
