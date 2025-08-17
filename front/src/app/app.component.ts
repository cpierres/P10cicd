import {Component, OnInit} from '@angular/core';
import { Observable } from 'rxjs';
import { Joke } from './model/joke.model';
import { JokesService } from './services/jokes.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  public joke$: Observable<Joke | null> = this.jokesService.joke$();

  constructor(private jokesService: JokesService) {
  }

  public ngOnInit(): void {
    console.log("fix du bug : Impossible de poster une suggestion de blague, le bouton tourne et fait planter mon navigateur");
    console.log("Ceci est la feature de possibilité de tchatter entre blagueurs (branche front-feat-add-tchat")
    console.log("the breaking change 4 est là !")
    this.getRandomJoke();
  }

  public getRandomJoke(): void {
    this.jokesService.getRandomJoke();
  }
}
