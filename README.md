### PACT  - Example Provider application

Simple application serving up a "get biscuits by id" api, to demonstrate the Provider side of pact contract testing. 

Part of the  [workshop steps here](https://github.com/csbiggar/pact-contract-testing-workshop)

### Prerequisites

* jvm 11+ (recommend installing via [sdkman](https://sdkman.io/))

### Run the application

    ./gradlew build run

then visit http://localhost:9000/biscuits/1
