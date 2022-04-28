package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.CartRepository;
import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.dto.CartDto;
import com.revature.spaceecobackend.dto.OrderDto;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.Cart;
import com.revature.spaceecobackend.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
  @Autowired
  CartRepository cartRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  public CartDto getCartByBuyerId(int buyerId) throws NotFound {
    if (userRepository.findById(buyerId).isPresent()) {
      Cart cart = cartRepository.findByBuyerId(buyerId);
      CartDto cartDto = modelMapper.map(cart, CartDto.class);

      return cartDto;
    } else {
      throw new NotFound("A user with an id of " + buyerId + " does not exist.");
    }
  }

  public CartDto createCart(CartDto cartDto) throws NotFound {
    if (cartDto.getBuyer() == null) {
      throw new NotFound("Please sign in/register");
    }

    Cart cart = modelMapper.map(cartDto, Cart.class);

    Cart createdCart = cartRepository.saveAndFlush(cart);
    return modelMapper.map(createdCart, CartDto.class);
  }

  public CartDto updateCart (CartDto cartDto) throws NotFound {
    if (cartDto.getBuyer() == null) {
      throw new NotFound("Please sign in/register");
    }

    Optional<Cart> optional = cartRepository.findById(cartDto.getId());
    if (!optional.isPresent()) throw new NotFound("Cart with id " + cartDto.getId() + " does not exist");
    Cart cart = modelMapper.map(cartDto, Cart.class);
    Cart updatedCart = cartRepository.saveAndFlush(cart);
    return modelMapper.map(updatedCart, CartDto.class);
  }
}
