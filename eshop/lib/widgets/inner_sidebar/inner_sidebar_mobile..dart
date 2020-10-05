import 'package:eshop/widgets/call_to_action/call_to_action.dart';
import 'package:flutter/material.dart';

class InnerSideBarMobile extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 80,
      child: Column(
        children: [
          Text(
            'CATEGORY',
            style: TextStyle(fontWeight: FontWeight.w800, height: 0.9),
          ),
          SizedBox(
            height: 10,
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
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
