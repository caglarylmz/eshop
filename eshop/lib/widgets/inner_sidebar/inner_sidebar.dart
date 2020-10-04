import 'package:eshop/widgets/inner_sidebar/inner_sidebar_desktop%20.dart';
import 'package:eshop/widgets/inner_sidebar/inner_sidebar_mobile..dart';
import 'package:flutter/material.dart';
import 'package:responsive_builder/responsive_builder.dart';

class InnerSideBar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ScreenTypeLayout(
      mobile: InnerSideBarMobile(),
      desktop: InnerSideBarDesktop(),
    );
  }
}
