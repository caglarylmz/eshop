import 'package:eshop/constants/app_colors.dart';
import 'package:eshop/widgets/call_to_action/call_to_action_desktop.dart';
import 'package:eshop/widgets/call_to_action/call_to_action_mobile.dart';
import 'package:flutter/material.dart';
import 'package:responsive_builder/responsive_builder.dart';

class CallToAction extends StatelessWidget {
  final String title;

  const CallToAction(this.title);

  @override
  Widget build(BuildContext context) {
    return ScreenTypeLayout(
      desktop: CallToActionDestop(title),
      tablet: CallToActionDestop(title),
      mobile: CallToActionMobile(title),
    );
  }
}
