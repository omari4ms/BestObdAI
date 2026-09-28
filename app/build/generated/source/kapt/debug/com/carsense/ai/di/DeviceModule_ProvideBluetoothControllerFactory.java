package com.carsense.ai.di;

import android.content.Context;
import com.carsense.ai.device.connection.bluetooth.BluetoothController;
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
public final class DeviceModule_ProvideBluetoothControllerFactory implements Factory<BluetoothController> {
  private final Provider<Context> contextProvider;

  public DeviceModule_ProvideBluetoothControllerFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public BluetoothController get() {
    return provideBluetoothController(contextProvider.get());
  }

  public static DeviceModule_ProvideBluetoothControllerFactory create(
      Provider<Context> contextProvider) {
    return new DeviceModule_ProvideBluetoothControllerFactory(contextProvider);
  }

  public static BluetoothController provideBluetoothController(Context context) {
    return Preconditions.checkNotNullFromProvides(DeviceModule.INSTANCE.provideBluetoothController(context));
  }
}
