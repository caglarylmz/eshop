import 'package:eshop/widgets/navbar/navbar_item.dart';
import 'package:eshop/widgets/navbar/navbar_logo.dart';
import 'package:flutter/material.dart';

class NavbarTabletDesktop extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 100,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          NavbarLogo(),
          Row(
            mainAxisSize: MainAxisSize.min,
            children: [
              NavBarItem('Üye Girişi'),
              SizedBox(
                width: 30,
              ),
              NavBarItem('İlan Ver')
            ],
          )
        ],
      ),
    );
  }
}
