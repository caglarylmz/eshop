import 'package:eshop/widgets/navbar/navbar_mobile.dart';
import 'package:eshop/widgets/navbar/navbar_tablet_desktop.dart';
import 'package:flutter/material.dart';
import 'package:responsive_builder/responsive_builder.dart';

class Navbar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ScreenTypeLayout(
      mobile: NavbarMobile(),
      tablet: NavbarTabletDesktop(),
      desktop: NavbarTabletDesktop(),
    );
  }
}
