package com.carsense.ai.di;

import android.content.Context;
import com.carsense.ai.device.connection.wifi.WifiController;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DeviceModule_ProvideWifiControllerFactory implements Factory<WifiController> {
  private final Provider<Context> contextProvider;

  public DeviceModule_ProvideWifiControllerFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public WifiController get() {
    return provideWifiController(contextProvider.get());
  }

  public static DeviceModule_ProvideWifiControllerFactory create(
      Provider<Context> contextProvider) {
    return new DeviceModule_ProvideWifiControllerFactory(contextProvider);
  }

  public static WifiController provideWifiController(Context context) {
    return Preconditions.checkNotNullFromProvides(DeviceModule.INSTANCE.provideWifiController(context));
  }
}
