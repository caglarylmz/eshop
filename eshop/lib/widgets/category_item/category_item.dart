import 'package:eshop/widgets/category_item/category_item_desktop.dart';
import 'package:eshop/widgets/category_item/category_item_mobile.dart';
import 'package:flutter/material.dart';
import 'package:responsive_builder/responsive_builder.dart';

class CategoryItem extends StatelessWidget {
  final String title;

  const CategoryItem(this.title);

  @override
  Widget build(BuildContext context) {
    return ScreenTypeLayout(
      desktop: CategoryItemDesktop(title),
      tablet: CategoryItemDesktop(title),
      mobile: CategoryItemMobile(title),
    );
  }
}
