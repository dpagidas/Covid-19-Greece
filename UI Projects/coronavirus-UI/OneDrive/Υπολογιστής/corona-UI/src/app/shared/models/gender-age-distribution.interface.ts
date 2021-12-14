import { MaleFemale } from "./male-female.interface";

export interface GenderAgeDistribution {
    id: number;
    males: MaleFemale;
    females: MaleFemale;
    updateAt: Date;
    malePercentageAgeDistribution: number;
    femalePercentageAgeDistribution: number;
  }