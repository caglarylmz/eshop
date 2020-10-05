import 'package:eshop/widgets/inner_sidebar/inner_sidebar.dart';
import 'package:flutter/material.dart';

class HomeViewDesktop extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Row(
          children: [
            InnerSideBar(),
            Container(
              child: Column(
                children: [],
              ),
            )
          ],
        )
      ],
    );
  }
}
