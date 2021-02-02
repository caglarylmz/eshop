import 'package:eshop/views/home/home_content/home_content_desktop.dart';
import 'package:eshop/views/home/home_content/home_content_mobile.dart';
import 'package:flutter/material.dart';
import 'package:responsive_builder/responsive_builder.dart';

class HomeContent extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ScreenTypeLayout(
      desktop: HomeContentDesktop(),
      tablet: HomeContentDesktop(),
      mobile: HomeContentMobile(),
    );
  }
}
