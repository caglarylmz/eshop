import 'package:eshop/widgets/call_to_action/call_to_action.dart';
import 'package:flutter/material.dart';

class InnerSideBarDesktop extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      width: 260,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text(
            'CATEGORY',
            style: TextStyle(fontWeight: FontWeight.w800, height: 0.9),
          ),
          SizedBox(
            height: 10,
          ),
          Column(
            children: [
              CallToAction("1"),
              CallToAction("2"),
              CallToAction("3"),
              CallToAction("4"),
              CallToAction("5"),
            ],
          )
        ],
      ),
    );
  }
}
