import 'package:eshop/constants/app_colors.dart';
import 'package:eshop/constants/app_fontssize.dart';
import 'package:flutter/material.dart';

class CategoryItemDesktop extends StatelessWidget {
  final String title;
  const CategoryItemDesktop(this.title);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 200,
      margin: EdgeInsets.symmetric(vertical: 4.0),
      alignment: Alignment.center,
      child: Text(
        title,
        style: TextStyle(
            fontSize: btnDesktopFontSize,
            fontWeight: FontWeight.w800,
            color: Colors.white),
      ),
      decoration: BoxDecoration(
          color: primaryColor, borderRadius: BorderRadius.circular(8)),
    );
  }
}
