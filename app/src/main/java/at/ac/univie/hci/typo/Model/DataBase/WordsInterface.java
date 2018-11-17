package at.ac.univie.hci.typo.Model.DataBase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public interface WordsInterface {


    public ArrayList<String> getList();


    public final String WORDS_STRING = "the\n" + "of\n" + "to\n" + "and\n" + "a\n" + "in\n" + "is\n" + "it\n" +
            "you\n" + "that\n" + "he\n" + "was\n" + "for\n" + "on\n" + "are\n" + "with\n" + "as\n" + "I\n" +
            "his\n" + "they\n" + "be\n" + "at\n" + "one\n" + "have\n" + "this\n" + "from\n" + "or\n" + "had\n" +
            "by\n" + "hot\n" + "word\n" + "but\n" + "what\n" + "some\n" + "we\n" + "can\n" + "out\n" + "other\n" + "were\n" +
            "all\n" + "there\n" + "when\n" + "up\n" + "use\n" + "your\n" + "how\n" + "said\n" + "an\n" + "each\n" +
            "she\n" + "which\n" + "do\n" + "their\n" + "time\n" + "if\n" + "will\n" + "way\n" + "about\n" + "many\n" +
            "then\n" + "them\n" + "write\n" + "would\n" + "like\n" + "so\n" + "these\n" + "her\n" + "long\n" + "make\n" +
            "thing\n" + "see\n" + "him\n" + "two\n" + "has\n" + "look\n" + "more\n" + "day\n" + "could\n" + "go\n" +
            "come\n" + "did\n" + "number\n" + "sound\n" + "no\n" + "most\n" + "people\n" + "my\n" + "over\n" +
            "know\n" + "water\n" + "than\n" + "call\n" + "first\n" + "who\n" + "may\n" + "down\n" + "side\n" +
            "been\n" + "now\n" + "find\n" + "any\n" + "new\n" + "work\n" + "part\n" + "take\n" + "get\n" + "place\n" +
            "made\n" + "live\n" + "where\n" + "after\n" + "back\n" + "little\n" + "only\n" + "round\n" + "man\n" +
            "year\n" + "came\n" + "show\n" + "every\n" + "good\n" + "me\n" + "give\n" + "our\n" + "under\n" +
            "name\n" + "very\n" + "through\n" + "just\n" + "form\n" + "sentence\n" + "great\n" + "think\n" + "say\n" +
            "help\n" + "low\n" + "line\n" + "differ\n" + "turn\n" + "cause\n" + "much\n" + "mean\n" + "before\n" + "move\n" +
            "right\n" + "boy\n" + "old\n" + "too\n" + "same\n" + "tell\n" + "does\n" + "set\n" + "three\n" + "want\n" + "air\n" +
            "well\n" + "also\n" + "play\n" + "small\n" + "end\n" + "put\n" + "home\n" + "read\n" + "hand\n" + "port\n" + "large\n" +
            "spell\n" + "add\n" + "even\n" + "land\n" + "here\n" + "must\n" + "big\n" + "high\n" + "such\n" + "follow\n" +
            "act\n" + "why\n" + "ask\n" + "men\n" + "change\n" + "went\n" + "light\n" + "kind\n" + "off\n" + "need\n" +
            "house\n" + "picture\n" + "try\n" + "us\n" + "again\n" + "animal\n" + "point\n" + "mother\n" + "world\n" +
            "near\n" + "build\n" + "self\n" + "earth\n" + "father\n" + "head\n" + "stand\n" + "own\n" + "page\n" + "should\n" + "country\n" +
            "found\n" + "answer\n" + "school\n" + "grow\n" + "study\n" + "still\n" + "learn\n" + "plant\n" + "cover\n" + "food\n" +
            "sun\n" + "four\n" + "between\n" + "state\n" + "keep\n" + "eye\n" + "never\n" + "last\n" + "let\n" + "thought\n" +
            "city\n" + "tree\n" + "cross\n" + "farm\n" + "hard\n" + "start\n" + "might\n" + "story\n" + "saw\n" + "far\n" + "sea\n" + "draw\n" + "left\n" + "late\n" + "run\n" + "dont\n" + "while\n" +
            "press\n" + "close\n" + "night\n" + "real\n" + "life\n" + "few\n" + "north\n" + "open\n" + "seem\n" +
            "together\n" + "next\n" + "white\n" + "children\n" + "begin\n" + "got\n" + "walk\n" + "example\n" + "ease\n" +
            "paper\n" + "group\n" + "always\n" + "music\n" + "those\n" + "both\n" + "mark\n" + "often\n" + "letter\n" + "until\n" +
            "mile\n" + "river\n" + "car\n" + "feet\n" + "care\n" + "second\n" + "book\n" + "carry\n" + "took\n" + "science\n" +
            "eat\n" + "room\n" + "friend\n" + "began\n" + "idea\n" + "fish\n" + "mountain\n" + "stop\n" + "once\n" + "base\n" +
            "hear\n" + "horse\n" + "cut\n" + "sure\n" + "watch\n" + "color\n" + "face\n" + "wood\n" + "main\n" + "enough\n" +
            "plain\n" + "girl\n" + "usual\n" + "young\n" + "ready\n" + "above\n" + "ever\n" + "red\n" + "list\n" + "though\n" +
            "feel\n" + "talk\n" + "bird\n" + "soon\n" + "body\n" + "dog\n" + "family\n" + "direct\n" + "pose\n" + "leave\n" +
            "song\n" + "measure\n" + "door\n" + "product\n" + "black\n" + "short\n" + "numeral\n" + "class\n" + "wind\n" +
            "question\n" + "happen\n" + "complete\n" + "ship\n" + "area\n" + "half\n" + "rock\n" + "order\n" + "fire\n" + "south\n" +
            "problem\n" + "piece\n" + "told\n" + "knew\n" + "pass\n" + "since\n" + "top\n" + "whole\n" + "king\n" + "space\n" +
            "heard\n" + "best\n" + "hour\n" + "better\n" + "true\n" + "during\n" + "hundred\n" + "five\n" + "remember\n" + "step\n" +
            "early\n" + "hold\n" + "west\n" + "ground\n" + "interest\n" + "reach\n" + "fast\n" + "verb\n" + "sing\n" + "listen\n" +
            "six\n" + "table\n" + "travel\n" + "less\n" + "morning\n" + "ten\n" + "simple\n" + "several\n" + "vowel\n" + "toward\n" +
            "war\n" + "lay\n" + "against\n" +
            "pattern\n" + "slow\n" + "center\n" + "love\n" + "person\n" + "money\n" + "serve\n" + "appear\n" + "road\n" + "map\n" +
            "rain\n" + "rule\n" +
            "govern\n" + "pull\n" + "cold\n" + "notice\n" + "voice\n" + "unit\n" + "power\n" + "town\n" + "fine\n" + "certain\n" +
            "fly\n" + "fall\n" + "lead\n" + "cry\n" + "dark\n" + "machine\n" + "note\n" + "wait\n" + "plan\n" + "figure\n" + "star\n" +
            "box\n" + "noun\n" + "field\n" + "rest\n" + "correct\n" + "able\n" + "pound\n" + "done\n" + "beauty\n" + "drive\n" +
            "stood\n" + "contain\n" + "front\n" + "teach\n" + "week\n" + "final\n" + "gave\n" + "green\n" + "oh\n" + "quick\n" +
            "develop\n" + "ocean\n" + "warm\n" + "free\n" + "minute\n" + "strong\n" + "special\n" + "mind\n" + "behind\n" + "clear\n" +
            "tail\n" + "produce\n" + "fact\n" + "street\n" + "inch\n" + "multiply\n" + "nothing\n" + "course\n" + "stay\n" + "wheel\n" +
            "full\n" + "force\n" + "blue\n" + "object\n" + "decide\n" + "surface\n" + "deep\n" + "moon\n" + "island\n" + "foot\n" + "system\n" +
            "busy\n" + "test\n" + "record\n" + "boat\n" + "common\n" + "gold\n" + "possible\n" + "plane\n" + "stead\n" + "dry\n" + "wonder\n" +
            "laugh\n" + "thousand\n" + "ago\n" + "ran\n" + "check\n" + "game\n" + "shape\n" + "equate\n" + "hot\n" + "miss\n" + "brought\n" +
            "heat\n" + "snow\n" + "tire\n" + "bring\n" + "yes\n" + "distant\n" + "fill\n" + "east\n" + "paint\n" + "language\n" + "among\n" +
            "grand\n" + "ball\n" + "yet\n" + "wave\n" + "drop\n" + "heart\n" + "am\n" + "present\n" + "heavy\n" + "dance\n" + "engine\n" + "position\n" +
            "arm\n" + "wide\n" + "sail\n" + "material\n" + "size\n" + "vary\n" + "settle\n" + "speak\n" + "weight\n" + "general\n" +
            "ice\n" + "matter\n" + "circle\n" + "pair\n" + "include\n" + "divide\n" + "syllable\n" + "felt\n" + "perhaps\n" + "pick\n" + "sudden\n" +
            "count\n" + "square\n" + "reason\n" + "length\n" + "represent\n" + "art\n" + "subject\n" + "region\n" + "energy\n" + "hunt\n" +
            "probable\n" + "bed\n" + "brother\n" + "egg\n" + "ride\n" + "cell\n" + "believe\n" + "fraction\n" + "forest\n" + "sit\n" + "race\n" +
            "window\n" + "store\n" + "summer\n" + "train\n" + "sleep\n" + "prove\n" + "lone\n" + "leg\n" + "exercise\n" + "wall\n" + "catch\n" +
            "mount\n" + "wish\n" + "sky\n" + "board\n" + "joy\n" + "winter\n" + "sat\n" + "written\n" + "wild\n" + "instrument\n" + "kept\n" +
            "glass\n" + "grass\n" + "cow\n" + "job\n" + "edge\n" + "sign\n" + "visit\n" + "past\n" + "soft\n" + "fun\n" + "bright\n" + "gas\n" +
            "weather\n" + "month\n" + "million\n" + "bear\n" + "finish\n" + "happy\n" + "hope\n" + "flower\n" + "clothe\n" + "strange\n" +
            "gone\n" + "jump\n" + "baby\n" + "eight\n" + "village\n" + "meet\n" + "root\n" +
            "buy\n" + "raise\n" + "solve\n" + "metal\n" + "whether\n" + "push\n" + "seven\n" + "paragraph\n" + "third\n" + "shall\n" + "held\n" +
            "hair\n" + "describe\n" + "cook\n" + "floor\n" + "either\n" + "result\n" + "burn\n" + "hill\n" + "safe\n" + "cat\n" + "century\n" +
            "consider\n" + "type\n" + "law\n" + "bit\n" + "coast\n" + "copy\n" + "phrase\n" + "silent\n" + "tall\n" + "sand\n" + "soil\n" + "roll\n" +
            "temperature\n" + "finger\n" + "industry\n" + "value\n" + "fight\n" + "lie\n" + "beat\n" + "excite\n" + "natural\n" + "view\n" + "sense\n" +
            "ear\n" + "else\n" + "quite\n" + "broke\n" + "case\n" + "middle\n" + "kill\n" + "son\n" + "lake\n" + "moment\n" + "scale\n" + "loud\n" +
            "spring\n" + "observe\n" + "child\n" + "straight\n" + "consonant\n" + "nation\n" + "dictionary\n" + "milk\n" + "speed\n" + "method\n" +
            "organ\n" + "pay\n" + "age\n" + "section\n" + "dress\n" + "cloud\n" + "surprise\n" + "quiet\n" + "stone\n" + "tiny\n" + "climb\n" + "cool\n" +
            "design\n" + "poor\n" + "lot\n" + "experiment\n" + "bottom\n" + "key\n" + "iron\n" + "single\n" + "stick\n" + "flat\n" + "twenty\n" + "skin\n" +
            "smile\n" + "crease\n" + "hole\n" + "trade\n" + "melody\n" + "trip\n" + "office\n" + "receive\n" + "row\n" + "mouth\n" + "exact\n" + "symbol\n" + "die\n" +
            "least\n" + "trouble\n" + "shout\n" + "except\n" + "wrote\n" + "seed\n" + "tone\n" + "join\n" + "suggest\n" + "clean\n" + "break\n" + "lady\n" + "yard\n" +
            "rise\n" + "bad\n" + "blow\n" + "oil\n" + "blood\n" + "touch\n" + "grew\n" + "cent\n" + "mix\n" + "team\n" + "wire\n" + "cost\n" + "lost\n" +
            "brown\n" + "wear\n" + "garden\n" + "equal\n" + "sent\n" + "choose\n" + "fell\n" + "fit\n" + "flow\n" + "fair\n" + "bank\n" + "collect\n" +
            "save\n" + "control\n" + "decimal\n" + "gentle\n" + "woman\n" + "captain\n" + "practice\n" + "separate\n" + "difficult\n" + "doctor\n" + "please\n" +
            "protect\n" + "noon\n" + "whose\n" + "locate\n" + "ring\n" + "character\n" + "insect\n" + "caught\n" + "period\n" + "indicate\n" + "radio\n" +
            "spoke\n" + "atom\n" + "human\n" + "history\n" + "effect\n" + "electric\n" + "expect\n" + "crop\n" + "modern\n" + "element\n" + "hit\n" + "student\n" +
            "corner\n" + "party\n" + "supply\n" + "bone\n" + "rail\n" + "imagine\n" + "provide\n" + "agree\n" + "thus\n" + "capital\n" + "wont\n" + "chair\n" + "danger\n" + "fruit\n" +
            "rich\n" + "thick\n" + "soldier\n" + "process\n" + "operate\n" + "guess\n" + "necessary\n" + "sharp\n" + "wing\n" + "create\n" + "neighbor\n" + "wash\n" +
            "bat\n" + "rather\n" + "crowd\n" + "corn\n" + "compare\n" + "poem\n" + "string\n" + "bell\n" + "depend\n" + "meat\n" + "rub\n" + "tube\n" + "famous\n" +
            "dollar\n" + "stream\n" + "fear\n" + "sight\n" + "thin\n" + "triangle\n" + "planet\n" + "hurry\n" + "chief\n" + "colony\n" + "clock\n" +
            "mine\n" + "tie\n" + "enter\n" + "major\n" + "fresh\n" + "search\n" + "send\n" + "yellow\n" + "gun\n" + "allow\n" + "print\n" + "dead\n" + "spot\n" +
            "desert\n" + "suit\n" + "current\n" + "lift\n" + "rose\n" + "continue\n" + "block\n" + "chart\n" + "hat\n" + "sell\n" + "success\n" + "company\n" +
            "subtract\n" + "event\n" + "particular\n" + "deal\n" + "swim\n" + "term\n" + "opposite\n" + "wife\n" + "shoe\n" + "shoulder\n" + "spread\n" +
            "arrange\n" + "camp\n" + "invent\n" + "cotton\n" + "born\n" + "determine\n" + "quart\n" + "nine\n" + "truck\n" + "noise\n" + "level\n" + "chance\n" +
            "gather\n" + "shop\n" + "stretch\n" + "throw\n" + "shine\n" + "property\n" + "column\n" + "molecule\n" + "select\n" + "wrong\n" + "gray\n" +
            "repeat\n" + "require\n" + "broad\n" + "prepare\n" + "salt\n" + "nose\n" + "plural\n" + "anger\n" + "claim\n" + "continent\n" + "oxygen\n" + "sugar\n" + "death\n" + "pretty\n" + "skill\n" + "women\n" + "season\n" + "solution\n" + "magnet\n" + "silver\n"
            + "thank\n" + "branch\n" + "match\n" + "suffix\n" + "especially\n" + "fig\n" + "afraid\n" + "huge\n" + "sister\n" + "steel\n" + "discuss\n" + "forward\n" + "similar\n" +
            "guide\n" + "experience\n" + "score\n" + "apple\n" + "bought\n" + "led\n" + "pitch\n" + "coat\n" + "mass\n" + "card\n" + "band\n" +
            "rope\n" + "slip\n" + "win\n" + "dream\n" + "evening\n" + "condition\n" + "feed\n" + "tool\n" + "total\n" + "basic\n" + "smell\n" + "valley\n" +
            "nor\n" + "double\n" + "seat\n" + "arrive\n" + "master\n" + "track\n" + "parent\n" + "shore\n" + "division\n" + "sheet\n" + "substance\n" + "favor\n" +
            "connect\n" + "post\n" + "spend\n" + "chord\n" + "fat\n" + "glad\n" + "original\n" + "share\n" + "station\n" + "dad\n" + "bread\n" +
            "charge\n" + "proper\n" + "bar\n" + "offer\n" + "segment\n" + "slave\n" + "duck\n" + "instant\n" + "market\n" + "degree\n" + "populate\n" +
            "chick\n" + "dear\n" + "enemy\n" + "reply\n" + "drink\n" + "occur\n" + "support\n" + "speech\n" + "nature\n" + "range\n" +
            "steam\n" + "motion\n" + "path\n" + "liquid\n" + "log\n" + "meant\n" + "quotient\n" + "teeth\n" + "shell\n" + "neck";



}
