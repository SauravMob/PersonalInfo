package controllers;

import play.mvc.Controller;
import java.util.*;

public class Trie extends Controller
{
    public static void autocomplete()
    {
        render();
    }
    static class TrieNode
    {
        char data;
        HashMap<Character, TrieNode> map = new HashMap<>();
        boolean isEnd = false;
        TrieNode(char c)
        {
            this.data = c;
        }
    }

    static class insert
    {
        TrieNode root = new TrieNode(' ');

        void put(String word)
        {
            TrieNode node = root;

            for (char ch : word.toCharArray())
            {
                if (!node.map.containsKey(ch))
                {
                    node.map.put(ch, new TrieNode(ch));
                }
                node = node.map.get(ch);
            }
            node.isEnd = true;
        }
        List<String> autocomplete(String prefix)
        {
            List<String> res = new LinkedList<String>();

            TrieNode node = root;
            for (char ch : prefix.toCharArray())
            {
                if (node.map.containsKey(ch))
                {
                    node = node.map.get(ch);
                }
                else
                {
                    return res;
                }
            }
            helper(node, res, prefix.substring(0, prefix.length()-1));
            return res;
        }

        void helper(TrieNode node, List<String> res, String prefix)
        {
            if (node.isEnd)
            {
                res.add(prefix + node.data);
            }
            for (Character ch : node.map.keySet())
            {
                helper(node.map.get(ch), res, prefix + node.data);
            }
        }
    }

    static insert i = new insert();
    static
    {
        i.put("saurav");
        i.put("neel");
        i.put("sumeet");
        i.put("shivam");
        i.put("karan");
        i.put("abhay");
        i.put("mayank");
    }

    public static void view(String prefix)
    {
        renderJSON(i.autocomplete(prefix));
    }
}