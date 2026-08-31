import math
from symtable import Symbol

# def is_palindrome(s):
#     str = s.lower()
#     strLength = len(str)-1
#     if len(str) <= 2:
#         return False
#     for i in range(math.floor(strLength/2)):
#         if(str[i] is not str[strLength-i]):
#             return False
#     return True

# def is_palindrome(s):
#     text = s.lower()
#     last = len(text) - 1

#     for i in range(len(text) // 2):
#         if text[i] != text[last - i]:
#             return False
#     return True

# def is_palindrome(s):
#     text = s.lower()
#     return text == text[::-1]

# s = "tD"
# print(is_palindrome(s))

# def add_length(str_):
#     words = str_.split(' ')
#     return map(str+" "+len(str),words)

# def _():
#     recipe = {"flour": 500, "sugar": 200, "eggs": 1}
#     available = {"flour": 1200, "sugar": 1200, "eggs": 5, "milk": 200}
#     test.assert_equals(cakes(recipe, available), 2, 'example #1')

# recipe = {"flour": 500, "sugar": 200, "eggs": 1}
# available = {"flour": 1200, "sugar": 1200, "eggs": 5, "milk": 200}

# def cakes(recipe, available):
#     values = []
#     for r in recipe:
#         if r not in available: return 0
#         values.append(available.get(r) // recipe.get(r))
#     return min(values)


# def disemvowel(s):
#     vowels = 'aeiouAEIOU'
#     for c in vowels: s = s.replace(c,"")
#     return s

# disemvowel("This website is for losers LOL!")



# def pick_peaks(arr):
#     pos = []
#     peaks = []
#     peak = None
#     for i in range(1,len(arr)):
#         if arr[i-1] < arr[i]: peak = i
#         if arr[i-1] > arr[i] and peak is not None:
#             pos.append(peak)
#             peaks.append(arr[peak])
#             peak = None
#     return {"pos": pos, "peaks": peaks} 

# print(pick_peaks([1,2,1,2,1]))

# def high(words):
#     res = []
#     wordsArray = words.split()
#     for word in wordsArray:
#         value = 0
#         for c in word: value += ord(c) - 96
#         res.append(value)
        
#     return wordsArray[res.index(max(res))]

# def high(words):
#     res = 0
#     ind = 0
#     wordsArray = words.split()
#     for i in range(len(wordsArray)):
#         temp = 0
#         for c in wordsArray: temp += ord(c) - 96
#         if res < temp: 
#             res = temp
#             ind = i
#     return ind
        
    

# print(high('man i need a taxi up to ubud'))

# def generate_hashtag(s):
#     res = "#"
#     for w in s.split(): res += w.capitalize()
#     return False if res > 140 or len(s) <= 0 else res

# print(generate_hashtag('CoDeWaRs is niCe'))

# def generate_hashtag(s):
#     if not s.strip(): return False
#     words = "#" + "".join(word.capitalize() for word in s.split())
#     return words if len(words) <= 140 else False

# print(generate_hashtag('CoDeWaRs is niCe'))

# def score(dices):
#     singleScore = { 1:100, 5:50 }
#     tripleScore = { 1:1000, 2:200, 3:300, 4:400, 5:500, 6:600 }
#     res = 0
#     for dice in list(dict.fromkeys(dices)):
#         count = dices.count(dice)
#         res += count // 3 * tripleScore.get(dice, 0) + count % 3 * singleScore.get(dice, 0)
#     return res
    

# print(score([1, 1, 1, 3, 1]))

# class RomanNumerals:
#     symbols = {"M":1000, "CM":900, "D":500, "CD":400, "C":100, "XC": 90, "L":50, "XL":40, "X":10, "IX":9, "V":5, "IV":4, "I":1}
    
#     @staticmethod
#     def to_roman(val : int) -> str: # 1990
#         res = ""
#         for num in RomanNumerals.symbols:
#             if val <= 0: break
#             value = RomanNumerals.symbols.get(num)
#             occurence = val // value
#             if occurence >= 1:
#                 val -= value * occurence
#                 res += num * occurence
#         return res

#     @staticmethod
#     def from_roman(roman_num : str) -> int: # MDCLXVI  MCMXC
#         res = 0
#         for num in RomanNumerals.symbols:
#             if len(roman_num) <= 0: break
#             while roman_num[:len(num)] == num:
#                 res += RomanNumerals.symbols.get(num)
#                 roman_num = roman_num[len(num):len(roman_num)]      
#         return res  
    
# # print(RomanNumerals().to_roman(2008)) # MMVIII
# # print(RomanNumerals().to_roman(2008)) # MMVIII
# print(RomanNumerals().from_roman("MMVIII")) # MMVIII
# print(RomanNumerals().from_roman("MCMXC")) # MCMXC

# class PaginationHelper:
#     collection = []
#     items_per_page = 0
    
#     # The constructor takes in an array of items and an integer indicating
#     # how many items fit within a single page
#     def __init__(self, collection, items_per_page):
#         self.collection = collection
#         self.items_per_page = items_per_page
    
#     # returns the number of items within the entire collection
#     def item_count(self):
#         return len(self.collection)  
    
#     # returns the number of pages
#     def page_count(self):
#         return math.ceil(self.item_count() / self.items_per_page)
    
#     # returns the number of items on the given page. page_index is zero based
#     # this method should return -1 for page_index values that are out of range
#     def page_item_count(self, page_index):
#         page_count = self.page_count()
#         if page_index < 0 or page_index > page_count - 1: return -1
#         if page_index < page_count - 1: return self.items_per_page 
#         rem = self.item_count() % self.items_per_page
#         return rem if rem != 0 else self.items_per_page
    
#     # determines what page an item at the given index is on. Zero based indexes.
#     # this method should return -1 for item_index values that are out of range
#     def page_index(self, item_index):
#         if item_index < 0 or item_index > self.item_count() - 1: return -1
#         # return math.ceil((item_index + 1) / self.items_per_page) - 1
#         return item_index // self.items_per_page


# collection = ['a','b','c','d','e','f']
# # collection = ['1','2','3','4']
# helper = PaginationHelper(collection, 4)
# # print(helper.page_count()) # should == 2
# # print(helper.item_count()) # should == 6
# print(helper.page_item_count(0)) # should == 4
# print(helper.page_item_count(1)) # last page - should == 2
# print(helper.page_item_count(2)) # should == -1 since the page is invalid
# print(helper.page_item_count(3)) # should == -1 since the page is invalid
# print(helper.page_item_count(4)) # should == -1 since the page is invalid

# # page_index takes an item index and returns the page that it belongs on
# print(helper.page_index(0)) # should == 1 (zero based index)
# print(helper.page_index(1)) # should == 1 (zero based index)
# print(helper.page_index(3)) # should == 1 (zero based index)
# print(helper.page_index(4)) # should == 1 (zero based index)
# print(helper.page_index(5)) # should == 1 (zero based index)
# print(helper.page_index(6)) # should == 0
# print(helper.page_index(20)) # should == -1
# print(helper.page_index(-10)) # should == -1 because negative indexes are invalid

# def snail(snail_map):
#     if len(snail_map) == 0: return []
#     n = len(snail_map)
#     res = [n*n]
    
#     for i in snail_map:
        
def tree_by_levels(node):   
    if not node: return []
    p, q = [], [node]
    
    while(q):
        n = q.popleft()
        p.append(n.value)
        if n.left is not None: q.append(n.left)
        if n.right is not None: q.append(n.right)
    
    return p


class Node:
    def __init__(self, L, R, n):
        self.left = L
        self.right = R
        self.value = n
