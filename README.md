[![Maintainability](https://api.codeclimate.com/v1/badges/23febc77a419cfde422e/maintainability)](https://codeclimate.com/github/jnwelzel/priceyyy/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/23febc77a419cfde422e/test_coverage)](https://codeclimate.com/github/jnwelzel/priceyyy/test_coverage)

# Algorithm
1. Iterate through cart items
1. Get item data, but from the base price list
1. Check which options are valid for this cart item using the data from the previous step and filter out the invalid options
1. Find the base price from the base price list for each item in the cart using its options + product type as unique keys
1. Perform price calculation using the data from both objects for each cart item

# Price Calculation Formula
- `(base_price + round(base_price * artist_markup in cents)) * quantity`

# Architecture
- I'm using Uncle Bob's Clean Architecture in combination with domain-driven design. Basically I'm grouping my Clean
Architecture concerns (gateways, use cases, presenters, etc) by their respective domains.

# How I did it
1. Abstracted the domain problem without caring about the underlying implementation using interfaces
1. Started working on the underlying implementations
   1. Cart item price calculator + tests
   1. Whole cart price calculator + tests
   1. Gateway that fetches the item with the base price from the JSON file + tests
1. Implemented the CLI app + tests
   1. The `AppTest` can be considered an integration or end to end test since it encompasses the whole logic of the `core` package

# Resources
- http://jsonpath.herokuapp.com

# How to run it
- run `mvn install`
- then either `java -jar target/priceyyy-1.0.0-SNAPSHOT-jar-with-dependencies.jar <cart_file_path> <base_prices_file>` or
`java -jar target/priceyyy-1.0.0-SNAPSHOT-jar-with-dependencies.jar` for interactive mode
