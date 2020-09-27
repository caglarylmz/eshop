import { SubCategory } from './subcategory.entity';

export class Category {
  id: number;
  name: string;
  icon: string;
  subCategories: SubCategory[];
}
