import 'package:eshop/views/home/home_content_desktop.dart';
import 'package:eshop/views/home/home_content_mobile.dart';
import 'package:eshop/widgets/centered_view/centered_view.dart';
import 'package:eshop/widgets/navbar/navbar.dart';
import 'package:flutter/material.dart';
import 'package:responsive_builder/responsive_builder.dart';

class HomeView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ResponsiveBuilder(
      builder: (context, sizingInformation) => Scaffold(
        backgroundColor: Colors.white,
        drawer: sizingInformation.deviceScreenType == DeviceScreenType.mobile
            ? Drawer()
            : null,
        body: Column(
          children: [
            Navbar(),
            CenteredView(
                child: Expanded(
                    child: ScreenTypeLayout(
              desktop: HomeContentDesktop(),
              mobile: HomeContentMobile(),
            )))
          ],
        ),
      ),
    );
  }
}
