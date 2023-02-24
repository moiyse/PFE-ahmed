import { BudgetInitial } from "./budgetInitial";
import { BudgetRevise } from "./budgetRevise";
import { Entreprise } from "./entreprise";

export class Direction{
    id:number;
    name:string;
    budgetInitials:BudgetInitial;
    budgetRevise:BudgetRevise;
    entreprise:Entreprise;
}
