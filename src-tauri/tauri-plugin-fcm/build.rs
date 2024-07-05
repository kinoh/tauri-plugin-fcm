const COMMANDS: &[&str] = &["get_token", "subscribe_to_topic"];

fn main() {
  tauri_plugin::Builder::new(COMMANDS)
    .android_path("android")
    .ios_path("ios")
    .build();
}
