// Region in which the resources are deployed.
param location string = 'westeurope'

/* Environment-specific suffix. */
var suffix = 'dev'

type storageConfig = object

func buildName(prefix string, suffix string) string => '${prefix}-${suffix}'

resource storage 'Microsoft.Storage/storageAccounts@2023-05-01' = if (location != '') {
  name: buildName('storage', suffix)
  location: location
}

module networking './network.bicep' = {
  name: 'networking'
}

var enabledResources = [for item in resources: if (item.enabled) {
  name: item.name
}]

output storageName string = storage.name
