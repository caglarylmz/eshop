import 'package:eshop/widgets/navbar/navbar_logo.dart';
import 'package:flutter/material.dart';

class NavbarMobile extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 80,
      child: Row(
        children: [
          Row(
            mainAxisSize: MainAxisSize.max,
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              IconButton(icon: Icon(Icons.menu), onPressed: null),
              NavbarLogo(),
            ],
          )
        ],
      ),
    );
  }
}
