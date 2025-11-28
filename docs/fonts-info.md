# AuraReminder Font Resources

## Overview
This directory contains Android font resources for the AuraReminder app.

## Files
- `fonts.xml` - Android font family definition with system font fallbacks

## Font Strategy
The app uses system font fallbacks (Roboto) for maximum compatibility:
- Poppins font was intended but not included due to licensing
- System fonts provide consistent appearance across devices
- Material Design typography guidelines followed

## Notes
- Font directory must only contain files with extensions: .xml, .ttf, .ttc, .otf
- No documentation files should be in this directory
- All font resources are merged during Android build process