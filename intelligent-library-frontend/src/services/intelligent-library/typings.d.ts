declare namespace API {
  type BankAccountRechargeReq = {
    rechargeMoney?: number;
    userId?: number;
  };

  type BankAccountVO = {
    id?: number;
    userBalance?: number;
    userId?: number;
    userTotalCost?: number;
  };

  type BaseResponseBankAccountVO_ = {
    code?: number;
    data?: BankAccountVO;
    message?: string;
  };

  type BaseResponseBigdecimal_ = {
    code?: number;
    data?: number;
    message?: string;
  };

  type BaseResponseBookInfo_ = {
    code?: number;
    data?: BookInfo;
    message?: string;
  };

  type BaseResponseBookVO_ = {
    code?: number;
    data?: BookVO;
    message?: string;
  };

  type BaseResponseBoolean_ = {
    code?: number;
    data?: boolean;
    message?: string;
  };

  type BaseResponseLoginUserVO_ = {
    code?: number;
    data?: LoginUserVO;
    message?: string;
  };

  type BaseResponseLong_ = {
    code?: number;
    data?: number;
    message?: string;
  };

  type BaseResponsePageBookInfo_ = {
    code?: number;
    data?: PageBookInfo_;
    message?: string;
  };

  type BaseResponsePageBookVO_ = {
    code?: number;
    data?: PageBookVO_;
    message?: string;
  };

  type BaseResponsePageUser_ = {
    code?: number;
    data?: PageUser_;
    message?: string;
  };

  type BaseResponsePageUserVO_ = {
    code?: number;
    data?: PageUserVO_;
    message?: string;
  };

  type BaseResponseUser_ = {
    code?: number;
    data?: User;
    message?: string;
  };

  type BookAddReq = {
    bookAuthor?: string;
    bookAvatar?: string;
    bookBorrowPrice?: number;
    bookDesc?: string;
    bookIsbn?: string;
    bookName?: string;
    bookPrice?: number;
    bookPublisher?: string;
    bookStock?: number;
    bookTags?: string;
    id?: number;
  };

  type BookInfo = {
    bookAuthor?: string;
    bookAvatar?: string;
    bookBorrowPrice?: number;
    bookDesc?: string;
    bookName?: string;
    bookPrice?: number;
    bookStock?: number;
    bookTags?: string;
    id?: number;
  };

  type BookQueryReq = {
    bookAuthor?: string;
    bookBorrowPrice?: number;
    bookDesc?: string;
    bookIsbn?: string;
    bookName?: string;
    bookPrice?: number;
    bookPublisher?: string;
    bookStock?: number;
    bookTags?: string;
    current?: number;
    id?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
  };

  type BookUpdateReq = {
    bookAuthor?: string;
    bookAvatar?: string;
    bookBorrowPrice?: number;
    bookDesc?: string;
    bookIsbn?: string;
    bookName?: string;
    bookPrice?: number;
    bookPublisher?: string;
    bookStock?: number;
    bookTags?: string;
    id?: number;
  };

  type BookVO = {
    bookAuthor?: string;
    bookAvatar?: string;
    bookBorrowPrice?: number;
    bookDesc?: string;
    bookIsbn?: string;
    bookName?: string;
    bookPrice?: number;
    bookPublisher?: string;
    bookStock?: number;
    bookTags?: string;
    id?: number;
  };

  type delBookByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getUserByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type IdReq = {
    id?: number;
  };

  type LoginUserVO = {
    gender?: number;
    id?: number;
    userAccount?: string;
    userAddress?: string;
    userAvatar?: string;
    userDesc?: string;
    userEmail?: string;
    userNickname?: string;
    userRole?: number;
  };

  type OrderInfo = {
    bookId?: number;
    createTime?: string;
    deleted?: number;
    id?: number;
    orderId?: number;
    orderInfoAmount?: number;
    orderInfoPayAmount?: number;
    updateTime?: string;
  };

  type OrderItem = {
    asc?: boolean;
    column?: string;
  };

  type OrderReq = {
    orderInfoList?: OrderInfo[];
    userId?: number;
  };

  type PageBookInfo_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: BookInfo[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageBookVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: BookVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageUser_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: User[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageUserVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: UserVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type User = {
    createTime?: string;
    deleted?: number;
    gender?: number;
    id?: number;
    updateTime?: string;
    userAccount?: string;
    userAddress?: string;
    userAvatar?: string;
    userDesc?: string;
    userEmail?: string;
    userNickname?: string;
    userPassword?: string;
    userPhone?: string;
    userRole?: number;
  };

  type UserAddReq = {
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userRole?: string;
  };

  type UserLoginReq = {
    userAccount?: string;
    userPassword?: string;
  };

  type UserQueryReq = {
    current?: number;
    id?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    userAccount?: string;
    userDesc?: string;
    userNickname?: string;
    userRole?: number;
  };

  type UserRegisterReq = {
    checkPassword?: string;
    userAccount?: string;
    userPassword?: string;
  };

  type UserUpdateReq = {
    gender?: number;
    id?: number;
    userAddress?: string;
    userAvatar?: string;
    userDesc?: string;
    userEmail?: string;
    userNickname?: string;
    userPhone?: string;
    userRole?: number;
  };

  type UserVO = {
    gender?: number;
    id?: number;
    userAccount?: string;
    userAddress?: string;
    userAvatar?: string;
    userDesc?: string;
    userEmail?: string;
    userNickname?: string;
    userPhone?: string;
    userRole?: number;
  };
}
