import 'package:eshop/views/home/home_content/home_content.dart';
import 'package:eshop/views/home/home_content/home_content_desktop.dart';
import 'package:eshop/widgets/inner_sidebar/inner_sidebar.dart';
import 'package:flutter/material.dart';

class HomeViewDesktop extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Row(
          children: [InnerSideBar(), HomeContent()],
        )
      ],
    );
  }
}
