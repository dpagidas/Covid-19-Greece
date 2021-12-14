import { CasesDeathsCriticals } from "./cases-deaths-criticals.interface";

export interface MaleFemale {
    id: number;
    cases: CasesDeathsCriticals;
    critical: CasesDeathsCriticals;
    deaths: CasesDeathsCriticals;
  }