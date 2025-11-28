# AuraReminder - Android Reminder App

A beautiful Android reminder app built with Material Three design and liquid glass UI effects. This app allows users to set specific days for reminders and provides elegant notifications with customizable actions.

## Features

### Core Functionality
- **Day-by-Day Calendar Selection**: Choose specific dates for reminders using an intuitive calendar interface
- **Smart Reminder Detection**: Automatically detects when you open the app on selected reminder days
- **Audio & Haptic Feedback**: Plays beep sound and provides vibration feedback for reminders
- **Dual Action Responses**: Choose between "Приехал к ней/еду" (Arrived/Going) or "Не хочу ехать!!!" (Don't want to go)
- **Result Screen**: Clean "Остановлено." (Stopped) screen with particle animation effects

### Design Features
- **Material Three Expressive Design**: Modern Material Design 3 implementation
- **Liquid Glass UI**: Glass morphism effects with backdrop blur and transparency
- **Animated Elements**: Breathing animation for reminder cards, particle effects on result screen
- **Gradient Backgrounds**: Dynamic radial gradient backgrounds
- **Custom Typography**: Poppins and Inter font families (with system fallbacks)
- **Responsive Layout**: Edge-to-edge display support for modern Android devices

## Technology Stack

- **Language**: Kotlin
- **UI Framework**: Android Views with Material Design 3
- **Architecture**: MVVM Pattern
- **Gradle**: 8.2
- **Target SDK**: 34 (Android 14)
- **Minimum SDK**: 24 (Android 7.0)
- **Build System**: Gradle with Android Gradle Plugin 8.2.0

## Dependencies

### Core Android
- `androidx.core:core-ktx` - Core Android extensions
- `androidx.appcompat:appcompat` - AppCompat library
- `androidx.constraintlayout:constraintlayout` - ConstraintLayout
- `androidx.fragment:fragment-ktx` - Fragment KTX extensions

### Material Design
- `com.google.android.material:material` - Material Design components
- `androidx.compose.material3:material3` - Material Design 3 Compose components

### UI Components
- `androidx.cardview:cardview` - CardView components
- `androidx.recyclerview:recyclerview` - RecyclerView for lists

### Preferences & Storage
- `androidx.preference:preference-ktx` - SharedPreferences KTX

## Project Structure

```
AuraReminder/
├── app/
│   ├── src/main/
│   │   ├── java/com/aura/reminder/
│   │   │   ├── MainActivity.kt          # Main activity with reminder logic
│   │   │   ├── ResultActivity.kt        # Result screen activity
│   │   │   ├── CalendarSelectorDialog.kt # Calendar date picker dialog
│   │   │   └── ReminderReceiver.kt      # Broadcast receiver for reminders
│   │   ├── res/
│   │   │   ├── layout/                  # XML layouts
│   │   │   │   ├── activity_main.xml    # Main activity layout
│   │   │   │   ├── activity_result.xml  # Result activity layout
│   │   │   │   └── dialog_calendar_selector.xml
│   │   │   ├── drawable/                # Vector drawables and shapes
│   │   │   │   ├── background_gradient.xml
│   │   │   │   ├── glass_card_background.xml
│   │   │   │   └── particle_background.xml
│   │   │   ├── values/                  # Resources
│   │   │   │   ├── colors.xml           # Color definitions
│   │   │   │   ├── strings.xml          # String resources
│   │   │   │   ├── themes.xml           # App themes
│   │   │   │   └── fonts.xml            # Font families
│   │   │   ├── anim/                    # Animation resources
│   │   │   │   └── particle_animation.xml
│   │   │   ├── mipmap/                  # App icons
│   │   │   └── xml/                     # XML configuration files
│   │   └── AndroidManifest.xml          # App manifest
│   ├── build.gradle                     # App-level build configuration
│   └── proguard-rules.pro               # ProGuard configuration
├── .github/
│   └── workflows/
│       └── android-ci.yml               # GitHub Actions CI/CD workflow
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties    # Gradle wrapper configuration
├── build.gradle                         # Project-level build configuration
├── settings.gradle                      # Gradle settings
├── gradle.properties                    # Gradle properties
├── gradlew                              # Gradle wrapper script (Unix)
└── gradlew.bat                          # Gradle wrapper script (Windows)
```

## Installation & Setup

### Prerequisites
- Android Studio Arctic Fox (2020.3.1) or later
- JDK 17 or later
- Android SDK 34
- Git

### Clone & Build
```bash
# Clone the repository
git clone https://github.com/your-username/AuraReminder.git
cd AuraReminder

# Build the project
./gradlew build

# Install on connected device
./gradlew installDebug
```

### GitHub Actions Setup
The project includes a comprehensive CI/CD pipeline that:
- Builds the project on every push and pull request
- Runs unit and instrumented tests
- Generates debug APK artifacts
- Runs on latest Ubuntu runner

## Usage

1. **Setting Reminders**: Tap the calendar button (FAB) to select reminder dates
2. **Receiving Reminders**: Open the app on selected days to see the reminder
3. **Responding**: Choose between arrival confirmation or decline options
4. **Result**: Both options lead to a clean result screen with "Остановлено."

## Design Implementation

### Material Three Components
- FloatingActionButton for calendar access
- MaterialButton for primary and secondary actions
- CardView with custom styling for glass morphism
- Custom color system following Material You palette

### Glass Morphism Effects
- Semi-transparent backgrounds with backdrop blur
- Gradient borders for depth perception
- Layered composition for glass-like appearance

### Animations
- Breathing animation for attention-drawing
- Particle explosion effect on result screen
- Smooth transitions between activities
- Haptic feedback integration

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Material Design 3 team for the design system
- Android development community for best practices
- Glass morphism design inspiration
- Google Fonts for typography inspiration