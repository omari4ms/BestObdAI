package com.carsense.ai.viewmodels;

import com.carsense.ai.device.connection.wifi.WifiController;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class WifiViewModel_Factory implements Factory<WifiViewModel> {
  private final Provider<WifiController> wifiControllerProvider;

  public WifiViewModel_Factory(Provider<WifiController> wifiControllerProvider) {
    this.wifiControllerProvider = wifiControllerProvider;
  }

  @Override
  public WifiViewModel get() {
    return newInstance(wifiControllerProvider.get());
  }

  public static WifiViewModel_Factory create(Provider<WifiController> wifiControllerProvider) {
    return new WifiViewModel_Factory(wifiControllerProvider);
  }

  public static WifiViewModel newInstance(WifiController wifiController) {
    return new WifiViewModel(wifiController);
  }
}
