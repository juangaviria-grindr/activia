package com.gaviria.activia.exceptions

class ResourceNotFoundException(message: String) : RuntimeException(message)

class BusinessException(message: String) : RuntimeException(message)