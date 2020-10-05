import 'package:eshop/constants/app_colors.dart';
import 'package:eshop/constants/app_fontssize.dart';
import 'package:flutter/material.dart';

class CallToActionMobile extends StatelessWidget {
  final String title;

  const CallToActionMobile(this.title);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 70,
      margin: EdgeInsets.symmetric(horizontal: 1.0),
      alignment: Alignment.center,
      child: Text(
        title,
        style: TextStyle(
            fontSize: btnMobileFontSize,
            fontWeight: FontWeight.w800,
            color: Colors.white),
      ),
      decoration: BoxDecoration(
          color: primaryColor, borderRadius: BorderRadius.circular(8)),
    );
  }
}
