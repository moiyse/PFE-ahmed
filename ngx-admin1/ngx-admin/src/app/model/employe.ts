import { Direction } from "./direction";
import { Entreprise } from "./entreprise";

export class Employe {
    id: number;
    nom: String;
    email: String;
    prenom: String;
    password:String;
    role: String;
    direction: Direction;
    isActif: boolean;
}