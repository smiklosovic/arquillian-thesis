if (we go to connect to physical device) {
  get physical device
  set physical device to injection point
  fire AndroidDeviceReady event; return
} else if (we go to connect to virtual device) {
  get virtual device
  set virtual device to injection point
  fire AndroidDeviceReady event; return
}
// we do not have any instance of running real
// or virtual device at this moment
if (device name is not specified) {
  generate random identifier as device name
  mark name of device as generated
}
if (generated device name does not already exist) {
  fire AndroidVirtualDeviceCreate event
} else {
  fire AndroidVirtualDeviceAvailable event
}
