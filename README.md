# Algorithm
1. Iterate through cart items
1. Find the base price from the base price file for each item in the cart
1. Perform price calculation

# Price Calculation Formula
- `(base_price + round(base_price * artist_markup in cents)) * quantity`

# Architecture
- I'm using Uncle Bob's Clean Architecture in combination with domain-driven design. Basically I'm grouping my Clean
Architecture concerns (gateways, use cases, presenters, etc) by their respective domains.

# How I did it
1. Abstracted the domain problem without caring about the underlying implementation using interfaces
1. Started working on the underlying implementations
   1. Cart item price calculator
   1. Whole cart price calculator
   1. Gateway that fetches the item with the base price

# Resources
- http://jsonpath.herokuapp.com
