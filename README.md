# Wear Vision

[![](https://img.shields.io/maven-central/v/me.chenhe/wearvision?style=flat-square)](https://search.maven.org/artifact/me.chenhe/wearvision)

> ⚠️Warning: This is a library under development, please do not use it in the production environment.
>
> ⚠️警告：此库正在开发中，请勿用于生产环境。

A library that helps you easily build a unified watch application UI. min sdk version is 21 so that it is compatible with [Ticwear](http://ticwear.com/ticwear/ticwear4.html) system.

## Usage

```kotlin
implementation("me.chenhe:wearvision:${version}")
```

### App Theme

Your app theme must be `Theme.WearVision` or something extend from it.

### Recycler View

All Recycler View's style is `@style/Widget.WearVision.ListView` by default. Your item layout's root should has `Widget.WearVision.ListItem` style so that you can get best experience.

### Dialog

Please use `AlertDialog` in `me.chenhe.wearvision.dialog` package. We don't have a Builder because it is recommended to use `apply` in kotlin. Here a sample:

```kotlin
AlertDialog(this).apply {
    title = "Dialog"
    message = "This is an ordinary dialog.
}.show()
```

### VisionCoordinatorLayout

Use `VisionCoordinatorLayout` rather than `CoordinatorLayout` so that you will get round edge effect.

### Preference

Use `me.chenhe.wearvision.preference.ListPreference` to get To get the appropriate dialog.

Always prefer `SwitchPreferenceCompat` to `CheckBoxPreference` according to the android [guidance](https://source.android.com/devices/tech/settings/settings-guidelines#checkbox). Meanwhile Wear Vision only optimizes the SwitchPreferenceCompat.

Use `PreferenceFragmentCompat` in package `me.chenhe.wearvision.preference` to ensure that all preference items work properly.

