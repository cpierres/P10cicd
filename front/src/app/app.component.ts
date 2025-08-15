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
    console.log("log de test FEAT   1825 - fix 2145");
    console.log("the breaking change 4 est là !")
    this.getRandomJoke();
  }

  public getRandomJoke(): void {
    this.jokesService.getRandomJoke();
  }
}
