import 'package:eshop/constants/app_colors.dart';
import 'package:eshop/constants/app_fontssize.dart';
import 'package:flutter/material.dart';

class CategoryItemMobile extends StatelessWidget {
  final String title;

  const CategoryItemMobile(this.title);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.symmetric(horizontal: 2.0),
      alignment: Alignment.center,
      child: Padding(
        padding: const EdgeInsets.all(3.0),
        child: Text(
          title,
          style: TextStyle(
              fontSize: btnMobileFontSize,
              fontWeight: FontWeight.w800,
              color: Colors.white),
        ),
      ),
      decoration: BoxDecoration(
          color: primaryColor, borderRadius: BorderRadius.circular(8)),
    );
  }
}
