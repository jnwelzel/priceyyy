# Algorithm
1. Iterate through cart items
1. Get item data, but from the base price list
1. Check which options are valid for this cart item, filter out the rest
1. Find the base price from the base price list for each item in the cart using its options + product type
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

# Resources
- http://jsonpath.herokuapp.com
